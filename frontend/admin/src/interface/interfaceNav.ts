export interface DefaultMenuType {
  name: string;
  icon: string;
  sort: number;
}

export interface SubMenuType extends DefaultMenuType {
  url: string;
}

export interface NavType extends DefaultMenuType {
  sub: Array<SubMenuType>;
}

export interface TabType {
  title: string;
  content: React.ReactNode;
}
