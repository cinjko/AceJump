package com.johnlindquist.acejump

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR
import com.intellij.openapi.project.DumbAwareAction
import com.johnlindquist.acejump.search.Pattern.LINE_MARK
import com.johnlindquist.acejump.ui.AceUI.editor
import java.awt.event.KeyEvent

object AceAction : DumbAwareAction() {
  override fun update(action: AnActionEvent) {
    action.presentation.isEnabled = (action.getData(EDITOR)) != null
  }

  override fun actionPerformed(e: AnActionEvent) {
    editor = e.getData(EDITOR) ?: editor
    KeyboardHandler.activate()
  }
}

object AceTargetAction : DumbAwareAction() {
  override fun update(action: AnActionEvent) = AceAction.update(action)

  override fun actionPerformed(e: AnActionEvent) {
    AceAction.actionPerformed(e)
    KeyboardHandler.toggleTargetMode(true)
  }
}

object AceLineAction : DumbAwareAction() {
  override fun update(action: AnActionEvent) = AceAction.update(action)

  override fun actionPerformed(e: AnActionEvent) {
    AceAction.actionPerformed(e)
    KeyboardHandler.findPattern(LINE_MARK)
  }
}

object AceKeyAction : DumbAwareAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val inputEvent = e.inputEvent as? KeyEvent ?: return
    KeyboardHandler.processCommand(inputEvent.keyCode)
  }
}