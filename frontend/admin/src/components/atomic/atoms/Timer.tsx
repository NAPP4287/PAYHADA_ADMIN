import { useEffect } from "react";
import { TimerProps } from "interface/InterfaceBasicLabel";

const Timer = (props: TimerProps) => {
  const { minutes, seconds, setMinutes, setSeconds, timerStyle, setIsEnd } =
    props;

  useEffect(() => {
    const countdown = setInterval(() => {
      if (seconds > 0) {
        setSeconds(seconds - 1);
      }
      if (seconds === 0) {
        if (minutes === 0) {
          setIsEnd(true);
          clearInterval(countdown);
        } else {
          setMinutes(minutes - 1);
          setSeconds(59);
        }
      }
    }, 1000);
    return () => clearInterval(countdown);
  }, [minutes, seconds, setIsEnd, setMinutes, setSeconds]);

  return (
    <span className="label" style={{ ...timerStyle }}>
      {minutes < 10 && 0}
      {minutes} : {seconds < 10 && 0}
      {seconds}
    </span>
  );
};

export default Timer;
