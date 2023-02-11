import { AnswerToSend } from './Answer-to-send';

export interface CompleteBallot {
  ballotId: number;
  questionWithAnswer: AnswerToSend[];
}
