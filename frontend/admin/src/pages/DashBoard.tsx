import { Card, CardBody } from "reactstrap";
// css
import styles from "assets/css/Dashboard.module.css";
// components
import DashboardDailyChart from "components/templetes/Dashboard/DashboardDailyChart";
import DashboardRemStatus from "components/templetes/Dashboard/DashboardRemStatus";
import DashboardExRateInfo from "components/templetes/Dashboard/DashboardExRateInfo";
import SelectCountry from "components/layout/SelectCountry";
// i18n
import { useTranslation } from "react-i18next";

const DashBoard = () => {
  const [t] = useTranslation();

  return (
    <div className={`main-content ${styles.dashWrap} contentWrap`}>
      <SelectCountry />

      <div className={styles.dashboardLeft}>
        <div className={styles.leftChartWrap}>
          <DashboardDailyChart t={t} />
        </div>

        <div className={styles.bottomLeftCardWrap}>
          <DashboardRemStatus t={t} />

          <Card>
            <CardBody>차트 미정 - 회의 필요</CardBody>
          </Card>
        </div>
      </div>
      <div className={styles.dashRight}>
        <div>
          <DashboardExRateInfo t={t} />
        </div>
        <div className={styles.rightBottomCard}>
          <Card style={{ height: "100%" }}>
            <CardBody>차트 미정 - 회의 필요</CardBody>
          </Card>
        </div>
      </div>
    </div>
  );
};

export default DashBoard;
