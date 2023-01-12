import {AnswerResponse} from "./answer-response";

export interface QuestionResponse{
  questionId: number
  ballotId: number
  orderNumber: number
  type: string
  questionContent: string
  answers: Set<AnswerResponse>
}
