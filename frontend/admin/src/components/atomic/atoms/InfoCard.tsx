import { Card, CardBody, CardTitle } from "reactstrap";
// css
import styles from "assets/css/Dashboard.module.css";
// interfaces
import { DashCardProps } from "interface/interfaceDashboard";

const InfoCard = (props: DashCardProps) => {
  const { idx, count, title, icon } = props;

  return (
    <Card key={idx} className={`mb-lg-0 ${styles.topCard}`}>
      <CardBody>
        <div className={styles.infoCardTop}>
          <CardTitle className="text-uppercase text-muted mb-0">
            {title}
          </CardTitle>
          <div className="icon icon-shape bg-primary text-white rounded-circle">
            <i className={icon} />
          </div>
        </div>
        <span className="font-weight-bold mb-0 textHidden">{count}</span>
      </CardBody>
    </Card>
  );
};

export default InfoCard;
