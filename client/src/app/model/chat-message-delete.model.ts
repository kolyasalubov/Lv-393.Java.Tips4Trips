
export class DeleteMessageInfoDTO {
  chatId:number;
  messageId: number;
  senderId: number;


  constructor(chatId: number, messageId: number, senderId: number) {
    this.chatId = chatId;
    this.messageId = messageId;
    this.senderId = senderId;
  }
}
