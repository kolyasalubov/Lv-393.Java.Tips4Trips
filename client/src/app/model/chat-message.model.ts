
export class ChatMessageInfoDTO {
    chatId: number;
    accountId: number;
    content : string;


  constructor(chatId: number, accountId: number, content: string) {
    this.chatId = chatId;
    this.accountId = accountId;
    this.content = content;
  }
}
