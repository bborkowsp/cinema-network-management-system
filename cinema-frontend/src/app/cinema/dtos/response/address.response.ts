export class AddressResponse {
  constructor(
    readonly streetAndBuildingNumber: string,
    readonly city: string,
    readonly postalCode: string,
    readonly country: string,
  ) {
  }
}
