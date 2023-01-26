export const test = () => {
  return "justTest";
};

export const addZero = (data: string) => {
  if (data === undefined) {
    return;
  }
  if (data.length <= 1) {
    return "0" + data;
  }
  return data;
};

export const addComma = (num: number) => {
  var regexp = /\B(?=(\d{3})+(?!\d))/g;
  return num.toString().replace(regexp, ",");
};
