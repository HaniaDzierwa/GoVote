import {PollType} from "./poll-type";

export interface AddQuestionDto {
  pollId: number;
  pollType: PollType;
  numberOfQuestion: number;
  question: string,
  answers: string[];
}
