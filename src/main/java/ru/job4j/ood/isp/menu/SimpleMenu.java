package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        if (parentName == null) {
            MenuItem menuItem = new SimpleMenuItem(childName, actionDelegate);
            rootElements.add(menuItem);
            rsl = true;
        } else {
            Optional<ItemInfo> parentOptional = findItem(parentName);
            if (parentOptional.isPresent()) {
                ItemInfo parent = parentOptional.get();
                parent.menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        MenuItemInfo menuItemInfo = null;
        Optional<ItemInfo> itemOptional = findItem(itemName);
        if (itemOptional.isPresent()) {
            ItemInfo info = itemOptional.get();
            menuItemInfo = new MenuItemInfo(info.menuItem, info.number);
        }
        return Optional.ofNullable(menuItemInfo);
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        List<MenuItem> menuItems = new ArrayList<>();
        flattenList(rootElements, menuItems);
        return menuItems.stream().map(s -> select(s.getName()).get()).iterator();
    }

    public void flattenList(List<MenuItem> inputList, List<MenuItem> outputList) {
        for (MenuItem list : inputList) {
            outputList.add(list);
            if (!list.getChildren().isEmpty()) {
                flattenList(list.getChildren(), outputList);
            }
        }
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator iterator = new DFSIterator();
        Optional<ItemInfo> rls = Optional.empty();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                rls = Optional.of(itemInfo);
            }
        }
        return rls;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
