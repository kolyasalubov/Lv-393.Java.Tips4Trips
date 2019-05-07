import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Message} from "../../model/message.model";
import {ChatMessageInfoDTO} from "../../model/chat-message.model";
import {ChatService} from "./chat.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {


   usernamePage = document.querySelector('#username-page');
   chatPage = document.querySelector('#chat-page');
   usernameForm = document.querySelector('#usernameForm');
   messageForm = document.querySelector('#messageForm');
   messageInput = document.querySelector('#message');
   messageArea = document.querySelector('#messageArea');
   connectingElement = document.querySelector('.connecting');
  serverUrl = "http://localhost:8080/ws/";
  private stompClient;
  isCustomSocketOpened = true;
  msg: String;
  messages: Message[] = [];
  testString: string;

   colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
  ];

  chatMessageInfo: ChatMessageInfoDTO = new ChatMessageInfoDTO(null,null,null);


  constructor(private chatService: ChatService) { }

  ngOnInit() {
    this.initializeWebSocketConnection();
    this.chatService.getMessagesByChatId(1).subscribe(data => this.messages = data);
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      console.log('Connecting stomp');
      console.log(frame);
      that.openSocket();
    });

  }

  openSocket() {
    console.log("opening socket");
    this.stompClient.subscribe("/topic/messages" , (message) => {
      console.log("subs to messages");
      this.handleResult(message);
      //this.onMessageReceived(message);
    });

  }

  //  onMessageReceived(payload) {
  //   console.log("onMessageRecive");
  //    let message = JSON.parse(payload.body);
  //    console.log("22");
  //    let messageElement = document.createElement('li');
  //
  //   // if(message.type === 'JOIN') {
  //   //   messageElement.classList.add('event-message');
  //   //   message.content = message.sender + ' joined!';
  //   // } else if (message.type === 'LEAVE') {
  //   //   messageElement.classList.add('event-message');
  //   //   message.content = message.sender + ' left!';
  //   // } else {
  //     messageElement.classList.add('chat-message');
  //
  //     let avatarElement = document.createElement('i');
  //     let avatarText = document.createTextNode("ddd");
  //     avatarElement.appendChild(avatarText);
  //     avatarElement.style['background-color'] = this.getAvatarColor("dddf");
  //
  //     messageElement.appendChild(avatarElement);
  //
  //     let usernameElement = document.createElement('span');
  //     let usernameText = document.createTextNode(message.sender);
  //     usernameElement.appendChild(usernameText);
  //     messageElement.appendChild(usernameElement);
  //  // }
  //
  //    let textElement = document.createElement('p');
  //    let messageText = document.createTextNode(message.content);
  //   textElement.appendChild(messageText);
  //
  //   messageElement.appendChild(textElement);
  //
  //    this.messageArea.appendChild(messageElement);
  //    this.messageArea.scrollTop = this.messageArea.scrollHeight;
  // }
  //
  //  getAvatarColor(messageSender) {
  //   let hash = 0;
  //   for (var i = 0; i < messageSender.length; i++) {
  //     hash = 31 * hash + messageSender.charCodeAt(i);
  //   }
  //
  //   let index = Math.abs(hash % this.colors.length);
  //   return this.colors[index];
  // }
  //
  handleResult(message) {
    console.log("handling result");
    console.log(message);
    console.log(JSON.parse(message.body));
    if (message.body) {
      let messageResult: Message = JSON.parse(message.body);
      console.log(messageResult);
      this.messages.push(messageResult);
      console.log(this.messages);
    }
  }

  sendMessage(message) {
    if (message) {
      this.chatMessageInfo.chatId=1;
      this.chatMessageInfo.accountId=1;
      this.chatMessageInfo.content=message;
      this.stompClient.send("/chat/send/message", {}, JSON.stringify(this.chatMessageInfo));
    }

  }


}
