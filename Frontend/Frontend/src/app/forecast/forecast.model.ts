export class Forecast {
    queryCost!: number;
    latitude!: number;
    longitude!: number;
    resolvedAddress!: string;
    address!: string;
    timezone!: string;
    tzoffset!: number;
    days!: Day[];
  }
  
  export class Day {
    datetime!: string;
    tempmax!: number;
    tempmin!: number;
    temp!: number;
    precip!: number;
    precipcover!: number;
    preciptype!: string | null;
    windspeedmax!: number;
    hours!: Hour[];
  }
  
  export class Hour {
    datetime!: string;
    temp!: number;
    precip!: number;
    preciptype!: string | null;
  }