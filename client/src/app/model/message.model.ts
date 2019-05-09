
export class Message {

  id: number;
  content: string;
  sendTime: string;
  senderId:number;
  senderFirstName: string;
  senderLastName: string;
  chatId: number;


  constructor(id: number, content: string, sendTime: string, senderId: number, senderFirstName: string, senderLastName: string, chatId: number) {
    this.id = id;
    this.content = content;
    this.sendTime = sendTime;
    this.senderId = senderId;
    this.senderFirstName = senderFirstName;
    this.senderLastName = senderLastName;
    this.chatId = chatId;
  }
}
