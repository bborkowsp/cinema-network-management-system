import {UserResponse} from "./user.response";

export class UserPageResponse {
  constructor(
    public content: UserResponse[],
    public totalElements: number,
  ) {
  }
}
