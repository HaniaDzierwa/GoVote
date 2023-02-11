export interface Authority {
  roleID: number;
  roleName: string;
  roleDescription: string;
  permVote: boolean;
  permCreateNewBallot: boolean;
  permDeleteOwnBallot: boolean;
  permDeleteAnyBallot: boolean;
  permViewUser: boolean;
  permDeleteUser: boolean;
  authority: string;
}
