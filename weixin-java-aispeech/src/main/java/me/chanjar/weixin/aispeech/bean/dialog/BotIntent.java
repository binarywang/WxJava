package me.chanjar.weixin.aispeech.bean.dialog;

import java.util.List;
import lombok.Data;

@Data
public class BotIntent {
  private String skill;
  private String intent;
  private Boolean disable;
  private List<String> questions;
  private List<String> answers;
}
