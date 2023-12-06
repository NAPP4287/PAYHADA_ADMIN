// components
import Title from "components/atomic/atoms/Title";
import ExchangeRateTab from "components/templetes/ExchangeRate/ExchangeRateTab";

const ExchangeRate = () => {
  return (
    <div className="main-content contentWrap">
      <Title title={"당발 환율"} />
      <ExchangeRateTab />
    </div>
  );
};

export default ExchangeRate;
