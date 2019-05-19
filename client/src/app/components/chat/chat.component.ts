import {AfterViewChecked, Component, ElementRef, Input, OnInit, ViewChild, ViewChildren} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Message} from "../../model/message.model";
import {ChatMessageInfoDTO} from "../../model/chat-message.model";
import {ChatService} from "./chat.service";
import {CustomAuthService} from "../authentication/custom-auth.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, AfterViewChecked {

  @Input() chatId: number;

  @ViewChild('content') content: ElementRef;

  serverUrl = "http://localhost:8080/ws/";
  private stompClient;
  currentAccountId: number;
  msg: String;
  messages: Message[] = [];
  @ViewChild('scrollMe') private myScrollContainer: ElementRef;

  chatMessageInfo: ChatMessageInfoDTO = new ChatMessageInfoDTO(null, null, null);


  constructor(private chatService: ChatService, private authService: CustomAuthService) {
  }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => this.currentAccountId = data.id);
    this.initializeWebSocketConnection();
    this.chatService.getMessagesByChatId(this.chatId).subscribe(data => this.messages = data);
    this.scrollToBottom();
  }


  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    try {
      this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    } catch (err) {
      console.log(err)
    }
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.openSocket();
    });

  }

  openSocket() {
    this.stompClient.subscribe("/topic/messages/" + this.chatId, (message) => {
      this.handleResult(message);
    });

  }

  handleResult(message) {
    JSON.parse(message.body);
    if (message.body) {
      let messageResult: Message = JSON.parse(message.body);
      this.messages.push(messageResult);
    }
  }

  sendMessage(message) {
    if (message) {
      this.chatMessageInfo.chatId = this.chatId;
      this.chatMessageInfo.accountId = this.currentAccountId;
      this.chatMessageInfo.content = message;
      this.stompClient.send("/chat/send/message", {}, JSON.stringify(this.chatMessageInfo));

    }

  }

  getImageString(id: number) {
    return "http://localhost:8080/accounts/" + id + "/image";
  }


}
