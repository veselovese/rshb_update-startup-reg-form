package ru.rshb.intech.digital.content.provider.api;

import ru.rshb.intech.digital.content.model.Book;
import ru.rshb.intech.digital.content.model.ItemType;

import jakarta.annotation.Nonnull;

public interface BooksContentService extends PublicationContentService<Book> {

  @Nonnull
  @Override
  default ItemType getAssotiatedItemType() {
    return ItemType.BOOK;
  }
}
