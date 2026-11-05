package lab10.src.task3;

import lab10.src.task1.Log;

public final class Task3Confirmation {
    private Task3Confirmation() {}

    public interface Confirmation {
        String render();
        void send(String bookingId);
    }

    public abstract static class BookingChannel {
        private final String name;

        protected BookingChannel(String name) {
            this.name = name;
        }

        public final void confirm(String bookingId) {
            Log.event(name, "Перевіряємо наявність номера для бронювання " + bookingId);
            Log.event(name, "Фіксуємо бронювання " + bookingId);
            Confirmation confirmation = createConfirmation();
            Log.event(name, "Сформовано підтвердження: " + confirmation.render());
            confirmation.send(bookingId);
        }

        protected abstract Confirmation createConfirmation();
    }

    private static final class ReceptionConfirmation implements Confirmation {
        @Override
        public String render() {
            return "друкований бланк підтвердження";
        }

        @Override
        public void send(String bookingId) {
            Log.event("Рецепція", "Друкуємо бланк для бронювання " + bookingId);
        }
    }

    public static final class ReceptionChannel extends BookingChannel {
        public ReceptionChannel() {
            super("Рецепція");
        }

        @Override
        protected Confirmation createConfirmation() {
            return new ReceptionConfirmation();
        }
    }

    private static final class WebsiteConfirmation implements Confirmation {
        @Override
        public String render() {
            return "електронний лист із підтвердженням";
        }

        @Override
        public void send(String bookingId) {
            Log.event("Сайт", "Надсилаємо лист для бронювання " + bookingId);
        }
    }

    public static final class WebsiteChannel extends BookingChannel {
        public WebsiteChannel() {
            super("Сайт");
        }

        @Override
        protected Confirmation createConfirmation() {
            return new WebsiteConfirmation();
        }
    }

    private static final class TourOperatorConfirmation implements Confirmation {
        @Override
        public String render() {
            return "файл підтвердження для системи туроператора";
        }

        @Override
        public void send(String bookingId) {
            Log.event("Туроператор", "Передаємо файл для бронювання " + bookingId);
        }
    }

    public static final class TourOperatorChannel extends BookingChannel {
        public TourOperatorChannel() {
            super("Туроператор");
        }

        @Override
        protected Confirmation createConfirmation() {
            return new TourOperatorConfirmation();
        }
    }

    private static final class OnlineServiceConfirmation implements Confirmation {
        @Override
        public String render() {
            return "підтвердження через API сервісу онлайн-бронювання";
        }

        @Override
        public void send(String bookingId) {
            Log.event("Онлайн-сервіс", "Передаємо підтвердження через API для бронювання " + bookingId);
        }
    }

    public static final class OnlineServiceChannel extends BookingChannel {
        public OnlineServiceChannel() {
            super("Онлайн-сервіс");
        }

        @Override
        protected Confirmation createConfirmation() {
            return new OnlineServiceConfirmation();
        }
    }
}
