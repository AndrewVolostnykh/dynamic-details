export class Detail {
  id: number;
  code: string;
  name: string;
  value: string | number | null;
  valueType: string;
  date: Date | null;
  chronoUnit: string | null;
  formula: string | null;

  constructor(id: number,
              name: string,
              code: string,
              value: string | number | null,
              valueType: string,
              date: Date | null,
              chronoUnit: string | null,
              formula: string | null) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.value = value;
    this.valueType = valueType;
    this.date = date;
    this.chronoUnit = chronoUnit;
    this.formula = formula;
  }

}
