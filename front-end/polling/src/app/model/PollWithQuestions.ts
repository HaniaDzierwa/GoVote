import { Question } from './Question';

export interface PollWithQuestions {
  ballotDescription: string;
  ballotName: string;
  ownerId: number;
  published: boolean;
  questions: Question[];
  ballotId?: number;
}
