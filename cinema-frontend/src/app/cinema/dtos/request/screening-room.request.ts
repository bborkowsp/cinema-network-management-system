export class ScreeningRoomRequest {
  constructor(
    readonly rows: number,
    readonly columns: number,
    readonly seatingPlan: any[][]
  ) {
  }
}
