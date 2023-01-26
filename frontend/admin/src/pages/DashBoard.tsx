import { Card, CardBody } from "reactstrap";
// data
import { topAllData } from "data/dashBoardData";
// css
import styles from "assets/css/Dashboard.module.css";
// components
import InfoCard from "components/atomic/atoms/InfoCard";
import DashboardChart from "components/templetes/DashboardChart";

const DashBoard = () => {
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

        <div className="smarginTop">
          <DashboardChart />
        </div>

        <div className={styles.bottomLeftCardWrap}>
          <Card className="card-stats mb-lg-0">
            <CardBody>ㅇㅇㅇ</CardBody>
          </Card>

          <Card className="card-stats mb-lg-0 marginLeft">
            <CardBody>ㅇㅇㅇ</CardBody>
          </Card>
        </div>
      </div>
      <div className={styles.dashRight}>
        <div className={styles.rightTopCard}>
          <Card className="card-stats mb-lg-0">
            <CardBody>ㅇㅇㅇ</CardBody>
          </Card>
        </div>
        <div className={styles.rightBottomCard}>
          <Card className="card-stats mb-lg-0">
            <CardBody>ㅇㅇㅇ</CardBody>
          </Card>
        </div>
      </div>
    </div>
  );
};

export default DashBoard;
