export class TwoWeeksForecast{
    queryCost!: number;
    latitude!: number;
    longitude!: number;
    resolvedAddress!: string;
    address!: string;
    timezone!: string;
    tzoffset!: number;
    days!: Day[];
}
export class Day{
    datetime!:string;
            tempmax!: number;
            tempmin!: number;
            precip!: number;
            preciptype!: string;
            windspeedmax!: number;
}
