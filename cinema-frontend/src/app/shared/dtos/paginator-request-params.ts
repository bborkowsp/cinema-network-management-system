export class PaginatorRequestParams {
  constructor(
    public page: number,
    public size: number,
    public sort?: string[],
  ) {}
}
