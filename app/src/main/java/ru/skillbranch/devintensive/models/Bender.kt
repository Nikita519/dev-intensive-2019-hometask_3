package ru.skillbranch.devintensive.models


class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {


    fun askQuestion(): String = when (question) {

        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.NAME.question
        Question.MATERIAL -> Question.NAME.question
        Question.BDAY -> Question.NAME.question
        Question.SERIAL -> Question.NAME.question
        Question.IDLE -> Question.NAME.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        return if (question.answers.contains(answer)){
            question = question.nextQuestion()
            "Отлично - это правильный ответ\n${question.question}" to status.color
        } else{
            status = status.nextStatus()
            "Это не правильный ответ\n${question.question}" to status.color
        }

    }

    enum class Status(val color: Triple<Int,Int,Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex){
                values()[this.ordinal+1]
            }else{
                values()[0]
            }
        }
    }



    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion() : Question
    }

}