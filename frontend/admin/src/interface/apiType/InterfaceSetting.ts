export interface ChangeLangReqType {
  languageCd: string;
  userNo?: string;
}

export interface ResultStatusResType {
  resultCode: string;
  resultMsg: string;
}

export interface TableListResType {
  trade_seq: string;
  nation_cd: string;
  fee: string;
  str_status: string;
  payment_type: string;
  send_amt: string;
}
