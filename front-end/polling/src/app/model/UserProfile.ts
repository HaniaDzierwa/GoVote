import { Authority } from './Authority';

export interface UserProfile {
  firstName: string;
  lastName: string;
  email: string;
  pesel: string;
  activationCode: string;
  changePasswordCode: null;
  passwordCodeExpDate: null;
  accountStatus: number;
  id: number;
  enabled: boolean;
  authorities: Authority[];
  username: string;
  password: string;
  credentialsNonExpired: boolean;
  accountNonExpired: boolean;
  accountNonLocked: boolean;
}
