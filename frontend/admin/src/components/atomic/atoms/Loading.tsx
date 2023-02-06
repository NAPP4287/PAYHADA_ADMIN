import { ThreeDots } from "react-loader-spinner";

const Loading = () => {
  return (
    <div className="alginCenter">
      <ThreeDots
        color="black"
        height="50"
        width="50"
        radius="9"
        ariaLabel="loading"
      />
    </div>
  );
};

export default Loading;
