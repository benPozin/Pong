import pygame
import random
from settings import Settings

class Ball:
    def __init__(self, x, y, radius):
        self.radius = radius
        self.rect = pygame.Rect(x - radius, y - radius, radius * 2, radius * 2)
        self.speed_x = 4 * random.choice((1, -1))
        self.speed_y = 4 * random.choice((1, -1))

    def move(self):
        self.rect.x += self.speed_x
        self.rect.y += self.speed_y

    def bounce_vertical(self):
        self.speed_y *= -1

    def bounce_horizontal(self):
        self.speed_x *= -1
        self.speed_x = max(min(self.speed_x * 1.1, 10), -10)  # Increase speed but cap it
        self.speed_y = max(min(self.speed_y * 1.1, 10), -10)

    def reset(self, x, y):
        self.rect.x = x - self.radius
        self.rect.y = y - self.radius
        self.speed_x = 4 * random.choice((1, -1))
        self.speed_y = 4 * random.choice((1, -1))

    def draw(self, screen):
        pygame.draw.ellipse(screen, Settings.WHITE, self.rect)
