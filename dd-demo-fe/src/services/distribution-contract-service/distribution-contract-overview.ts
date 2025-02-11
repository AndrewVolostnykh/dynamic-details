export class DistributionContractOverview {
  id: number;
  name: string;
  startDate: Date;

  constructor(id:number, name:string, startDate: Date) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
  }
}
