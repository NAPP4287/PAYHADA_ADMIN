import { Card, CardBody, CardTitle } from "reactstrap";
// interfaces
import { DashCardProps } from "interface/InterfaceDashboard";

const InfoCard = (props: DashCardProps) => {
  const { idx, count, title, icon } = props;

  return (
    <Card key={idx} className={`mb-lg-0 topCard`}>
      <CardBody>
        <div className={"infoCardTop"}>
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
