export interface DashCardProps {
  idx: number;
  title: string;
  count: string;
  icon: string;
}

export interface DailyChartProps {
  day: number | string;
  userCount: number | null;
  certUserCount: number | null;
  txnCount: number | null;
  remittance: number | null;
}

export interface ChartSeriesType {
  name: string;
  data: Array<string | number>;
}
