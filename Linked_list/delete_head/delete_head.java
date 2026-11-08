void deleteHead() {
    if (head == null) {
        return;
    }

    head = head.next;
}