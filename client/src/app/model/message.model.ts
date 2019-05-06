
export class Message {

  id: number;
  content: number;
  sendTime: string;
  firstName: string;
  lastName: string;
  chatId: number;


  constructor(id: number, content: number, sendTime: string, firstName: string, lastName: string, chatId: number) {
    this.id = id;
    this.content = content;
    this.sendTime = sendTime;
    this.firstName = firstName;
    this.lastName = lastName;
    this.chatId = chatId;
  }
}
