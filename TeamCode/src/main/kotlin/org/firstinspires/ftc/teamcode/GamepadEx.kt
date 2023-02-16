package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.Gamepad

private const val TRIGGER_THRESHOLD = 0.2

typealias Event = Pair<GamepadEx.Button, GamepadEx.RisingOrFalling>
typealias Handler = () -> Unit

class GamepadEx(private val gamepad: Gamepad) {
	enum class Button {
		A,
		B,
		X,
		Y,
		LB,
		RB,
		LT,
		RT,
		UP,
		DOWN,
		LEFT,
		RIGHT,
		BACK,
		GUIDE,
		START,
		LS,
		RS,
	}

	enum class RisingOrFalling {
		RISING,
		FALLING,
	}

	private var previous = Gamepad()
	private var current = Gamepad()

	private val handlers: MutableMap<Event, MutableSet<Handler>> = mutableMapOf()

	val leftX: Double get() = current.left_stick_x.toDouble()
	val leftY: Double get() = current.left_stick_y.toDouble()
	val rightX: Double get() = current.right_stick_x.toDouble()
	val rightY: Double get() = current.right_stick_y.toDouble()
	val LT: Double get() = current.left_trigger.toDouble()
	val RT: Double get() = current.right_trigger.toDouble()

	fun tick() {
		previous.copy(current)
		current.copy(gamepad)

		for (button in Button.values()) {
			if (didRise(button))
				handlers[button to RisingOrFalling.RISING]?.forEach { it() }
			else if (didFall(button))
				handlers[button to RisingOrFalling.FALLING]?.forEach { it() }
		}
	}

	fun addButtonHandler(event: Event, handler: Handler) {
		val list = handlers[event] ?: run {
			handlers[event] = mutableSetOf()
			handlers[event]!!
		}
		list += handler
	}

	fun removeButtonHandler(event: Event, handler: Handler) {
		val list = handlers[event] ?: return
		list.remove(handler)
	}

	fun getButton(button: Button): Boolean = getButtonPair(button).second
	private fun didRise(button: Button): Boolean =
		getButtonPair(button).let { !it.first && it.second }

	private fun didFall(button: Button): Boolean =
		getButtonPair(button).let { it.first && !it.second }

	private fun getButtonPair(button: Button) =
		when (button) {
			Button.A -> previous.a to current.a
			Button.B -> previous.b to current.b
			Button.X -> previous.x to current.x
			Button.Y -> previous.y to current.y
			Button.LB -> previous.left_bumper to current.left_bumper
			Button.RB -> previous.right_bumper to current.right_bumper
			Button.LT -> (previous.left_trigger >= TRIGGER_THRESHOLD) to (current.left_trigger >= TRIGGER_THRESHOLD)
			Button.RT -> (previous.right_trigger >= TRIGGER_THRESHOLD) to (current.right_trigger >= TRIGGER_THRESHOLD)
			Button.UP -> previous.dpad_up to current.dpad_up
			Button.DOWN -> previous.dpad_down to current.dpad_down
			Button.LEFT -> previous.dpad_left to current.dpad_left
			Button.RIGHT -> previous.dpad_right to current.dpad_right
			Button.BACK -> previous.back to current.back
			Button.GUIDE -> previous.guide to current.guide
			Button.START -> previous.start to current.start
			Button.LS -> previous.left_stick_button to current.left_stick_button
			Button.RS -> previous.right_stick_button to current.right_stick_button
		}
}