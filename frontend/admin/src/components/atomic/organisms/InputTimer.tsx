import React, { useState, useEffect, useCallback } from "react";
import { InputTimerProps } from "interface/InterfaceBasicLabel";
import { Button, Label } from "reactstrap";
import Timer from "components/atomic/atoms/Timer";

const InputTimer = (props: InputTimerProps) => {
  const {
    label,
    type,
    setChangeData,
    value,
    maxLength,
    placeholder,
    failedText,
    isFailed,
    resetTime,
    seconds,
    setSeconds,
    isEnd,
    setIsEnd,
  } = props;
  const [minutes, setMinutes] = useState<number>(0);

  const calcTime = useCallback(
    (seconds: number) => {
      setSeconds(seconds % 60);
      setMinutes(Math.floor(seconds / 60));
    },
    [setSeconds],
  );

  useEffect(() => {
    calcTime(seconds);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const inputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setChangeData(e.target.value);
  };

  const resetTimer = () => {
    calcTime(resetTime);
    setIsEnd(false);
    setChangeData("");
  };

  return (
    <div>
      <Label for={type} className="label">
        {label}
      </Label>
      <div className="inputWrap modalHeader">
        <input
          className="inputNone"
          placeholder={placeholder}
          type={type}
          onChange={(e) => inputChange(e)}
          value={value}
          maxLength={maxLength}
        />
        {isEnd ? (
          <Button
            color="secondary"
            type="button"
            className="label"
            onClick={resetTimer}>
            재요청
          </Button>
        ) : (
          <Timer
            seconds={seconds}
            setSeconds={setSeconds}
            minutes={minutes}
            setMinutes={setMinutes}
            setIsEnd={setIsEnd}
            isEnd={isEnd}
          />
        )}
      </div>
      {!isFailed && <span className="errorText">{failedText}</span>}
    </div>
  );
};

export default InputTimer;
