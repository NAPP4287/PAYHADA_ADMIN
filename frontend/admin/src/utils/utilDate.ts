export const latestYear = new Date().getFullYear();
export const latestMonth = new Date().getMonth() + 1;

export const selectYear = () => {
  const selectYearArr: Array<string | number> = ["Year"];
  for (let i: number = 2022; i <= latestYear; i++) {
    selectYearArr.push(i);
  }

  return selectYearArr;
};

export const selectMonth = () => {
  const selectMonthArr: Array<string | number> = ["Month"];

  for (let i: number = 1; i < 13; i++) {
    selectMonthArr.push(i);
  }

  return selectMonthArr;
};
