import { Card, CardBody } from "reactstrap";
// data
import { topAllData } from "data/dashBoardData";
// css
import styles from "assets/css/Dashboard.module.css";
// components
import InfoCard from "components/atomic/atoms/InfoCard";
import DashboardDailyChart from "components/templetes/DashboardDailyChart";
import DashboardRemStatus from "components/templetes/DashboardRemStatus";
import DashboardExRateInfo from "components/templetes/DashboardExRateInfo";
// i18n
import { useTranslation } from "react-i18next";

const DashBoard = () => {
  const [t, i18n] = useTranslation();

  console.log(i18n.language, t);

  return (
    <div className={`main-content ${styles.dashWrap}`}>
      <div className={styles.dashboardLeft}>
        <div className={styles.leftTopCard}>
          {topAllData.map((el, idx) => (
            <InfoCard
              key={idx}
              idx={idx}
              count={el.count}
              title={el.title}
              icon={el.icon}
            />
          ))}
        </div>

        <div className={`smarginTop ${styles.leftChartWrap}`}>
          <DashboardDailyChart />
        </div>

        <div className={styles.bottomLeftCardWrap}>
          <DashboardRemStatus />

          <Card>
            <CardBody>차트 미정 - 회의 필요</CardBody>
          </Card>
        </div>
      </div>
      <div className={styles.dashRight}>
        <div>
          <DashboardExRateInfo />
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
