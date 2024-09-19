export enum SeatZone {
  STANDARD = 'STANDARD',
  VIP = 'VIP',
  PROMO = 'PROMO',
  WHEELCHAIR = 'WHEELCHAIR',
  CORRIDOR = 'CORRIDOR'
}

export const SeatPrices: { [key in SeatZone]: string } = {
  [SeatZone.STANDARD]: '27,90 ZŁ',
  [SeatZone.VIP]: '35,90 ZŁ',
  [SeatZone.PROMO]: '19,90 ZŁ',
  [SeatZone.WHEELCHAIR]: '19,90 ZŁ',
  [SeatZone.CORRIDOR]: ''
};
