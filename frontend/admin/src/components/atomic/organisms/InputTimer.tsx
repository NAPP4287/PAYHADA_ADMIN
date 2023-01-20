/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useEffect } from "react";
import { InputTimerProps } from "interface/InterfaceBasicLabel";
import { Button, Label } from "reactstrap";
import Timer from "components/atomic/atoms/Timer";
import { onKeyPressEnter } from "utils/utilInput";

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
    onEnter,
  } = props;
  const [minutes, setMinutes] = useState<number>(0);
  const [enterAble, setEnterAble] = useState<boolean>(false);

  useEffect(() => {
    calcTime(seconds);
  }, []);

  const inputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.value.length === 6) {
      setEnterAble(true);
    }
    setChangeData(e.target.value);
  };

  const resetTimer = () => {
    calcTime(resetTime);
    setIsEnd(false);
    setChangeData("");
  };

  const calcTime = (seconds: number) => {
    setSeconds(seconds % 60);
    setMinutes(Math.floor(seconds / 60));
  };

  const handleEnterPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (isEnd && e.key === "Enter") {
      return e.preventDefault();
    }
    if (!isEnd) {
      return onKeyPressEnter(e, onEnter, enterAble);
    }
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
          onKeyPress={(e) => handleEnterPress(e)}
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
