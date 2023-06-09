export interface Answer {
  answerId: number;
  questionId: number;
  responderToken: number;
  answerText: string;
  answerOrderNumber: number;
  userAnswer: string | boolean | number;
}
