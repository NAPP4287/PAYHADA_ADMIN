import { Card, CardBody, CardTitle, Row, Col } from "reactstrap";
// data
import { topAllData } from "data/dashBoardData";

const DashBoard = () => {
  return (
    <div
      className="main-content"
      style={{ display: "flex", padding: "50px 30px", flexWrap: "wrap" }}>
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          backgroundColor: "pink",
          flex: "3",
        }}>
        {topAllData.map((el, idx) => (
          <div key={idx} style={{ width: "18rem", marginRight: "15px" }}>
            <Card className="card-stats mb-4 mb-lg-0">
              <CardBody>
                <Row>
                  <div className="col">
                    <CardTitle className="text-uppercase text-muted mb-0">
                      {el.title}
                    </CardTitle>
                    <span className="font-weight-bold mb-0">{el.count}</span>
                  </div>
                  <Col className="col-auto">
                    <div className="icon icon-shape bg-primary text-white rounded-circle">
                      <i className={el.icon} />
                    </div>
                  </Col>
                </Row>
              </CardBody>
            </Card>
          </div>
        ))}
      </div>
      <div style={{ flex: "1", backgroundColor: "orange" }}>
        <div style={{ width: "100%", backgroundColor: "white" }}>??</div>
      </div>
    </div>
  );
};

export default DashBoard;
