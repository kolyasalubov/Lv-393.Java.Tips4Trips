import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Message} from "../../model/message.model";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  serverUrl = "http://localhost:8080/ws/";
  private stompClient;
  isCustomSocketOpened = true;
  msg: String;
  messages: Message[] = [];
  testString: string;

  constructor() { }

  ngOnInit() {
    this.initializeWebSocketConnection();
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
    });

  }

  handleResult(message) {
    console.log("handling result");
    console.log(message);
    console.log(JSON.parse(message.body));
    if (message.body) {
      let messageResult: Message = JSON.parse(message.body);
      console.log(messageResult);
      this.messages.push(messageResult);


    }
  }

  sendMessage(message) {
    if (message) {
      this.stompClient.send("/chat/send/message", {}, message);
    }

  }


}
