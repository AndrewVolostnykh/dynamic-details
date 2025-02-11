import {Detail} from './detail';

export class DistributionContract {
  delayFine:number;
  depreciationRate:number;
  insuranceRate:number;
  price:number;
  deadLine:Date;
  startDate:Date;
  id:number;
  description:string;
  name:string;

  details: Detail[]

  constructor(id: number,
              name: string,
              delayFine: number,
              depreciationRate: number,
              insuranceRate: number,
              price: number,
              deadLine: Date,
              startDate: Date,
              description: string,
              details: Detail[]) {
    this.delayFine = delayFine;
    this.depreciationRate = depreciationRate;
    this.insuranceRate = insuranceRate;
    this.price = price;
    this.deadLine = deadLine;
    this.startDate = startDate;
    this.id = id;
    this.description = description;
    this.name = name;
    this.details = details;
  }
}
