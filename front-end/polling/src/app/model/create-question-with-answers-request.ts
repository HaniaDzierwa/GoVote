export interface CreateQuestionWithAnswersRequest{
  ballotId: number
  orderNumber: number
  questionType: string
  questionContent: string
  answers: string[]
}
