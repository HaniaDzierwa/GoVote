import { Answer } from './Answer';

export interface Question {
  questionId: number;
  ballotId: number;
  answers: Answer[];
  orderNumber: number;
  type: string;
  questionContent: string;
}
