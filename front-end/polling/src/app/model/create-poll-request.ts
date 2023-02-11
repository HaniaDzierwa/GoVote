export interface CreatePollRequest {
  ballotName: string | undefined | null;
  ballotDescription: string | undefined | null;
  ownerId: number;
  published: boolean;
}
