package lab11.src.task1;

public class Task1RoomBooking {

    interface Room {
        String getDescription();

        double getCost();
    }

    static class StandardRoom implements Room {
        @Override
        public String getDescription() {
            return "Стандартний номер";
        }

        @Override
        public double getCost() {
            return 1500.0;
        }
    }

    abstract static class RoomServiceDecorator implements Room {
        protected final Room room;

        RoomServiceDecorator(Room room) {
            this.room = room;
        }

        @Override
        public String getDescription() {
            return room.getDescription();
        }

        @Override
        public double getCost() {
            return room.getCost();
        }
    }

    static class Breakfast extends RoomServiceDecorator {
        Breakfast(Room room) {
            super(room);
        }

        @Override
        public String getDescription() {
            return room.getDescription() + ", сніданок";
        }

        @Override
        public double getCost() {
            return room.getCost() + 250.0;
        }
    }

    static class MountainView extends RoomServiceDecorator {
        MountainView(Room room) {
            super(room);
        }

        @Override
        public String getDescription() {
            return room.getDescription() + ", вид на гори";
        }

        @Override
        public double getCost() {
            return room.getCost() + 300.0;
        }
    }

    static class LateCheckout extends RoomServiceDecorator {
        LateCheckout(Room room) {
            super(room);
        }

        @Override
        public String getDescription() {
            return room.getDescription() + ", пізній виїзд";
        }

        @Override
        public double getCost() {
            return room.getCost() + 200.0;
        }
    }

    static class Parking extends RoomServiceDecorator {
        Parking(Room room) {
            super(room);
        }

        @Override
        public String getDescription() {
            return room.getDescription() + ", паркування";
        }

        @Override
        public double getCost() {
            return room.getCost() + 150.0;
        }
    }

    static class BabyCot extends RoomServiceDecorator {
        BabyCot(Room room) {
            super(room);
        }

        @Override
        public String getDescription() {
            return room.getDescription() + ", дитяче ліжко";
        }

        @Override
        public double getCost() {
            return room.getCost() + 100.0;
        }
    }
}
