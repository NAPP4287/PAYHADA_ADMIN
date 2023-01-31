import { Card, CardBody, CardTitle, Row, Col } from "reactstrap";
// css
import styles from "assets/css/Dashboard.module.css";
// interfaces
import { DashCardProps } from "interface/interfaceDashboard";

const InfoCard = (props: DashCardProps) => {
  const { idx, count, title, icon } = props;

  return (
    <Card key={idx} className={`card-stats mb-lg-0 ${styles.topCard}`}>
      <CardBody>
        <Row>
          <div className="col">
            <CardTitle className="text-uppercase text-muted mb-0 textHidden">
              {title}
            </CardTitle>
            <span className="font-weight-bold mb-0 textHidden">{count}</span>
          </div>
          <Col className="col-auto">
            <div className="icon icon-shape bg-primary text-white rounded-circle">
              <i className={icon} />
            </div>
          </Col>
        </Row>
      </CardBody>
    </Card>
  );
};

export default InfoCard;
