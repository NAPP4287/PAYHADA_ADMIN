import { Table, Card, CardBody } from "reactstrap";
// data
import { remStatusData } from "data/dashBoardData";
// util
import { addComma } from "utils/utilCommon";
// interfaces
import { ITranslation } from "interface/InterfaceCommon";

const DashboardRemStatus = ({ t }: ITranslation) => {
  return (
    <Card>
      <CardBody>
        <span className={`boxTitle chartTitle`}>Remittance status</span>
        <Table className="align-items-center smarginTop" responsive>
          <thead className="thead-light">
            <tr>
              <th scope="col">{t("Common.remStatus")}</th>
              <th scope="col">{t("Common.all")}</th>
              <th scope="col">{t("Common.onGoing")}</th>
              <th scope="col">{t("Common.complete")}</th>
              <th scope="col">{t("Common.cancel")}</th>
            </tr>
          </thead>

          <tbody>
            {remStatusData.map((el: any, idx: number) => (
              <tr key={idx}>
                <td>
                  <span className="mb-0 text-sm">
                    {t(`Dashboard.${el.title}`)}
                  </span>
                </td>
                <td>{addComma(el.all)}</td>
                <td>{addComma(el.process)}</td>
                <td>{addComma(el.complete)}</td>
                <td>{addComma(el.cancel)}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </CardBody>
    </Card>
  );
};

export default DashboardRemStatus;
