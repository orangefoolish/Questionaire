package com.example.classproject.entity.question;

import lombok.Data;

@Data
public class Questions {
    Selectqs []selectqs;
    Multiselectqs []multiselectqs;
    Blankqs []blankqs;
    Statementqs []statementqs;
}
