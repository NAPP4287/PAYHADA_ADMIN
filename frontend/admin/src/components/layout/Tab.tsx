import { useState } from "react";
import { Nav, NavItem, NavLink, TabContent, TabPane } from "reactstrap";
// interface
import { TabType } from "interface/InterfaceNav";

const Tab = (props: any) => {
  const { tabList } = props;

  const [tabToggle, setTabToggle] = useState(0);

  const handleTab = (idx: number) => {
    setTabToggle(idx);
  };

  return (
    <>
      {/* tab */}
      <Nav tabs>
        {tabList.map((el: TabType, idx: number) => (
          <NavItem key={idx}>
            <NavLink
              className={tabToggle === idx ? "active" : "unactive"}
              onClick={() => handleTab(idx)}>
              {el.title}
            </NavLink>
          </NavItem>
        ))}
      </Nav>
      <TabContent activeTab={tabToggle}>
        {tabList.map((el: TabType, idx: number) => (
          <TabPane key={idx} tabId={idx}>
            {el.content}
          </TabPane>
        ))}
      </TabContent>
    </>
  );
};

export default Tab;
